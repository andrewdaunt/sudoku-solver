package com.example;

public class SudokuTest {
    public static void main(String[] args){
        String[] testBoards = {
            "010060072050090008000000001008019000002004500400685000000000007800400060006000000",   
            "000080005851409000400020800068007900040000007190003204004000060000002000019670000",   
            "200079100037502080000040020020000000010000208976128000760034000103000070000760930",    
            "000008020900001047063420008306000000200190035007030009070980013000000000540603000",    
            "100400000396000040200703000672030980031609007049800603060100420010062300928004001",   
            "000608470000030900250917638700501304000006017800740065070800000060370509530020040",    
            "000000000000000000000000000000000000000000000000000000000000000000000000000000000"
        };
        
        int count = 0;
        int passed = 0;
        SudokuSolver s = new SudokuSolver();
        for(String board : testBoards){
            String result = s.solvePuzzle(board);
            if(result != board){
                passed += 1;
                System.out.println("PASSED");
            } else{
                System.out.println("FAILED");
            }
            count += 1;
        }
        System.out.println("Finished all tests. Passed: " + passed + "/" + count);
    }
}
