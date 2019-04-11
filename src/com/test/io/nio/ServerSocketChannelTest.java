package com.test.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import com.test.BaseTester;

public class ServerSocketChannelTest extends AbstractNioTester {

	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// ssc.register(sel, ops, att)
		ssc.configureBlocking(false);
		ssc.bind(new InetSocketAddress("127.0.0.1", 9000), 1);
		// 等等accept的最大超时时间，似乎不生效
		ssc.socket().setSoTimeout(2000);
		// ssc.setOption(SocketOption<T>, value)
		AcceptThread acceptThread = new AcceptThread(ssc);
		acceptThread.run();
//		acceptThread.join();
	}

	static class AcceptThread extends Thread {
		private final Selector acceptSelector;
		private final ServerSocketChannel ssc;
		private final SelectorThread selectorThread;
		public AcceptThread(ServerSocketChannel ssc) throws IOException {
			this.acceptSelector = SelectorProvider.provider().openSelector();
			this.ssc = ssc;
			this.selectorThread = new SelectorThread();
		}

		@Override
		public void run() {
			try {
				selectorThread.start();
				ssc.register(acceptSelector, SelectionKey.OP_ACCEPT);
				while (true) {
					select();
				}
			} catch (IOException e) {
				e.printStackTrace(System.err);
//				("Got an IOException while selecting!", e);
			}
		}
		
		private void select() throws IOException {
			// wait for io events.
			acceptSelector.select();
			// process the io events we received
			Iterator<SelectionKey> selectedKeys = acceptSelector.selectedKeys().iterator();
			while (selectedKeys.hasNext()) {
				SelectionKey key = selectedKeys.next();
				selectedKeys.remove();
				if(!key.isValid()) {
					continue;
				}
				if(key.isAcceptable()) {
					SocketChannel sc = ssc.accept();
					tPrint("new socket connection " + sc);
					selectorThread.addAccept(sc);
				} else {
					tPrint("unexpected key ops");
				}
			}
		}
	}

	static class SelectorThread extends Thread {
		Selector selector;
		ArrayBlockingQueue<SocketChannel> queue = new ArrayBlockingQueue<>(3);

		public SelectorThread() throws IOException {
			this.selector = SelectorProvider.provider().openSelector();
		}

		@Override
		public void run() {
			try {
				while (true) {
					select();
					while (true) {
						SocketChannel sc = queue.poll(); //这里是有非阻塞的提取已连接的sc
						if(sc == null) break;
						printSocketChannel(sc);
						tPrint("take socketChannel, left="+queue.size());
						sc.register(selector, SelectionKey.OP_READ);	
					}
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

		private void select() {
			try {
				int i = selector.select();
				tPrint("select return i=" + i);
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					if (key.isValid()) {
						tPrint("all ops:" + Integer.toBinaryString(key.readyOps()));
						if (key.isAcceptable()) {

						} else if (key.isReadable()) {
							handleRead(key);
						} else if (key.isWritable()) {

						} else {
							tPrint("unknown SelectionKey Ops:" + key.readyOps());
						}
					} else {
						tPrint("invalid selectedKey found, " + key);
					}
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

		private void handleRead(SelectionKey key) throws IOException {
			SocketChannel channel = (SocketChannel) key.channel();
			ByteBuffer bf = ByteBuffer.allocate(10);
			int readLen = 0;
			bf.clear();
			StringBuffer sb = new StringBuffer();
			while ((readLen = channel.read(bf)) > 0) {
				sb.append(new String(bf.array()));
				bf.clear();
			}
			// if(-1==readLen){
			// channel.close();
			// }
			channel.write(ByteBuffer.wrap(("客户端，你传过来的数据是：" + sb.toString()).getBytes()));
		}

		public void addAccept(SocketChannel sc) {
			queue.add(sc);
			tPrint("add new Connect SocketChannel, total="+queue.size());
			selector.wakeup();
		}
		
	}
}
