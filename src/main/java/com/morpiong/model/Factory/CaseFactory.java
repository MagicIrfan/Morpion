package com.morpiong.model.Factory;

import com.morpiong.model.Case;
import com.morpiong.view.CasePane;

public class CaseFactory{
    public static Case create(){
        return new Case(new CasePane());
    }
}
