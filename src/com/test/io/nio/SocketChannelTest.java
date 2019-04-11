package com.test.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Scanner;

public class SocketChannelTest extends AbstractNioTester {

	private static volatile int stat=0;
	
	public static void main(String[] args) throws IOException {
		new ConnectThread().start();
		Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
        	if(sc.hasNextInt()) {
        		stat = sc.nextInt();
            	if(stat == -1) {
            		tPrint("exit.........");
            		break;
            	}
                //利用nextXXX()方法输出内容
//                String str = sc.next();
                tPrint("keyboard input "+stat);	
        	} else {
        		tPrint("invalid input: "+sc.nextLine());
        		continue;
			}
        }
	}

	static class ConnectThread extends Thread {
		private boolean hasWrited = false;
		@Override
		public void run() {
			Thread.currentThread().setName("ConnectThread-"+Thread.currentThread().getId());
			SocketChannel sc = null;
			Selector selector = null;
			try {
				sc = SocketChannel.open();
				selector = SelectorProvider.provider().openSelector();
				sc.connect(new InetSocketAddress("127.0.0.1", 9000));
				while (!sc.finishConnect()) {
					
				}
				tPrint("socketChannel connected");
//				printSocketChannel(sc);
//				if(sc.is)
				sc.register(selector, 
						SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
				while (true) {
					if(stat == 0) {
						Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
						while (iterator.hasNext()) {
							SelectionKey key = iterator.next();
							if(key.isValid()) {
								tPrint("all ops:"+Integer.toBinaryString(key.readyOps()));
								if(key.isConnectable()) {
									
								} else if (key.isReadable()) {
									handleRead(sc);
								} else if (key.isWritable()) {
									if(!hasWrited) {
										handleWrite(sc);		
										hasWrited = true;	
									}
								} else {
									tPrint("unknown SelectionKey Ops:"+key.readyOps());
								}
							} else {
								tPrint("invalid selectedKey found, "+key);
							}
							iterator.remove();
						}	
					} else if (stat == 1) {
						if(sc.isConnected()) {
							handleWrite(sc);
							hasWrited = true;
						} else {
							tPrint("not read for write, isBlocking="+sc.isBlocking());
							hasWrited = false;
						}
						stat = 0;
					} else if (stat == -1) {
						tPrint("close connect, sc="+sc);
						break;
					}
				}	
			} catch (Exception e) {
				e.printStackTrace(System.err);
			} finally {
				try {
					if(sc != null) sc.close();
					if(selector != null) {
						selector.close();
					}	
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
				}
			}
		}
		
		private static void handleRead(SocketChannel sc) throws IOException {
//			SocketChannel sc = (SocketChannel)key.channel();
			ByteBuffer bf = ByteBuffer.allocate(1000);
			int len=0;
			while ((len=sc.read(bf))>0) {
				tPrint("pos="+bf.position()+", limit="+bf.limit()+", remain="+bf.remaining()+", cap="+bf.capacity());
			}
			tPrint("resp from server: "+new String(bf.array()));
		}
		
		private static void handleWrite(SocketChannel sc) throws IOException {
			String msg = "hello world";
			ByteBuffer src = ByteBuffer.allocate(msg.getBytes().length);
			src.put(msg.getBytes());
			src.flip();
			int len = sc.write(src);
			tPrint("write byte "+len);
			
		}
	}
}
