package com.morpiong.model.Player;

import com.morpiong.model.Case;

public interface PlayableStrategy {
    void chooseCase(Case[][] cases);
}
