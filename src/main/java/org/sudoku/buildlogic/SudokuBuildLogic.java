package org.sudoku.buildlogic;

import org.sudoku.computationlogic.GameLogic;
import org.sudoku.persistence.LocalStorageImpl;
import org.sudoku.problemdomain.IStorage;
import org.sudoku.problemdomain.SudokuGame;
import org.sudoku.userinterface.IUserInterfaceContract;
import org.sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {


    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {

            initialState = storage.getGameData();
        } catch (IOException e) {

            initialState = GameLogic.getNewGame();

            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}