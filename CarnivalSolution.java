/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carnivalsolution;

import java.util.concurrent.Semaphore;

/**
 *
 * @author user
 */
public class CarnivalSolution {
        public static Semaphore seats=new Semaphore(10);
        private static Semaphore canPlay=new Semaphore(0);
        private static Semaphore newCycle = new Semaphore(0);
        private static Semaphore lock=new Semaphore(1);
        private static int groupNo=0;
        private static int totalNo=0;
    

	public static void init() {
	}

	public static class Participant extends TemplateThread {

		public Participant(int numRuns) {
			super(numRuns);
		}

		@Override
		public void execute() throws InterruptedException {
		seats.acquire();
                state.participantEnter();
                lock.acquire();
                groupNo++;
                if(groupNo==10){
                    canPlay.release(10);
                }
                lock.release();
                canPlay.acquire();
                state.present();
                lock.acquire();
                groupNo--;
                totalNo++;
                if(groupNo==0){
                    state.endGroup();
                    seats.release(10);
                }
                if(totalNo==30){
                    state.endCycle();
                    newCycle.release();
                    totalNo=0;
                }
                lock.release();
                newCycle.acquire();
		}
                

	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			run();
		}
	}

	static CarnivalState state = new CarnivalState();

	public static void run() {
		try {
			int numRuns = 15;
			int numThreads = 30;

			HashSet<Thread> threads = new HashSet<Thread>();

			for (int i = 0; i < numThreads; i++) {
				Participant c = new Participant(numRuns);
				threads.add(c);
			}

			init();

			ProblemExecution.start(threads, state);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    
}
