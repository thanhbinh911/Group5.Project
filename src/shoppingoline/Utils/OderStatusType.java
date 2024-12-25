/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoppingoline.Utils;

/**
 *
 * @author choco
 */
public enum OderStatusType {
    ON_PROCESS("Đang giao hàng"),
    DONE("Đã giao hàng"),;
    private String desc;

    public String getDesc() {
        return desc;
    }

    OderStatusType(String desc) {
        this.desc = desc;
    }

}
