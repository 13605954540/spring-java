package com.lp.first.learn.learn.builder;

import org.springframework.data.domain.Sort;

/**
 * @author LP
 * @date 2019/4/14
 */
public class Runner {

    public static void main(String[] args) {
        Model model = Director.direct();
        System.err.println(model.toString());
    }
}
