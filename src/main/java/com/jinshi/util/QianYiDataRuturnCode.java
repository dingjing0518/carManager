package com.jinshi.util;

import net.sdk.bean.Data_E_ReturnCode;

public class  QianYiDataRuturnCode implements Data_E_ReturnCode {
    public static enum E_ReturnCode {
        DC_UNDEFINED_ERROR("未知错误", -1),
        DC_NO_ERROR("正常", 0),
        DC_HANDLE_INVALID("无效的句柄", 1),
        DC_CONN_FAIL("连接失败", 2),
        DC_OBJ_BUSY("对象忙", 3),
        DC_OBJ_UNEXIST("对象不存在", 4),
        DC_CMD_INVALID("命令无效", 5),
        DC_PARA_INVALID("参数无效", 6),
        DC_REQ_TIMEOUT("请求超时", 7),
        DC_MEMORY_LACK("内存申请失败", 8),
        DC_SEND_FAIL("数据发送失败", 9),
        DC_RECV_FAIL("数据接收失败", 10),
        DC_OPT_FAIL("操作失败", 11),
        DC_NOT_CONN("没有触发连接", 12),
        DC_BEYOND_MAX_CLIENT("超出相机最大连接数量", 13),
        DC_CONNECT_AUTH("连接鉴权", 18),
        DC_USER_NOT_EXIST("用户不存在", 19),
        DC_PASSWD_ERROR("密码错误", 20);

        private int value = 0;
        private String name = null;

        private E_ReturnCode(int temp) {
            this.value = temp;
        }

        private E_ReturnCode(String name, int i) {
            this.value = i;
            this.name = name;
        }

        public int getValue() {
            return this.value;
        }

        public static String getName(int index) {
            for (E_ReturnCode c : E_ReturnCode.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }

        public int getIndex() {
            return value;
        }
    }
}
