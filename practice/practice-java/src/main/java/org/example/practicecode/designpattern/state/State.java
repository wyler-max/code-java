package org.example.practicecode.designpattern.state;

import com.example.designpattern.state.Context;

/**
 * 状态接口
 */
public interface State {
    void doAction(Context context);
}
