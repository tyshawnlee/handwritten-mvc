package com.tyshawn.pattern;

/**
 * @author litianxiang
 */
public class AccountProxy implements IAccountService {
    //目标对象
    private IAccountService target;

    public AccountProxy() {
    }

    public AccountProxy(IAccountService target) {
        this.target = target;
    }

    /**
     * 代理方法,实现对目标方法的功能增强
     */
    @Override
    public void transfer() {
        before();
        target.transfer();
    }

    /**
     * 前置增强
     */
    private void before() {
        System.out.println("对转账人身份进行验证.");
    }
}
