package com.dzfp.dzfp.approval;

public interface ApprovalInterface {
    /**
     * 创建报销流程
     *
     * @param companyId 公司id
     * @param ids       用户id集合
     */
    void addProcess(String processname,String companyId, String ids);

    /**
     * 获取公司报销流程列表
     *
     * @param companyId 公司id
     */
    void listComProcess(String companyId);

    /**
     * 同意申请
     *
     * @param orderid 流程id
     * @param bz      备注
     */
    void orderPass(String orderid, String bz);

    /**
     * 不同意申请
     *
     * @param orderid 流程id
     * @param bz      备注
     */
    void orderNotPass(String orderid, String bz);

    /**
     * 待我审批
     *
     * @param companyId 公司id
     */
    void orderAuditOfMe(String companyId);

    /**
     * 获取员工列表
     *
     * @param companyId 公司id
     */
    void listComUser(String companyId);

    /**
     * 报销申请
     *
     * @param companyId 公司id
     * @param processid 报销流程id
     * @param fpids     发票集合
     */
    void addOrder(String companyId, String processid, String fpids);

    /**
     * 取消申请
     *
     * @param orderid 流程id
     */
    void invokeProcess(String orderid);

    /**
     * 查看报销申请流程详情
     *
     * @param orderid 流程id
     */
    void checkOrderDetail(String orderid);

    /**
     * 我发的申请
     *
     * @param companyId 公司id
     */
    void orderOfMe(String companyId);
}
