package com.xinglongjian.thread.guardblock;

public class Drop {
	// Message sent from producer
    // to consumer.
	private String message;
	// True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
	private boolean empty=true;

	public synchronized String take(){
		System.out.println("start take......");
		while(empty){
			try {
				System.out.println("take() wait! start");
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("take() wait! end");
		// Toggle status.
		empty=true;
		// Notify producer that
        // status has changed.
		notifyAll();
		
		return message;
	}
	
	public synchronized void put(String message){
		// Wait until message has
        // been retrieved.
		System.out.println("start put......");
		while(!empty){
			try {
				System.out.println("put() wait! start");
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("put() wait! end");
		// Toggle status.
		empty=false;
		// Store message.
		this.message=message;
		 // Notify consumer that status
        // has changed.
		notifyAll();
	}
	
	
	
	
}
