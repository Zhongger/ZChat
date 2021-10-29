package com.zhongger.zchat.VO;


import com.zhongger.zchat.DTO.ContactPersonInfo;
import lombok.Data;

import java.util.List;

@Data
public class ReContactPerson {
    private Integer code;
    private List<ContactPersonInfo> contactPersonInfos;
    private Boolean istrue;

    public ReContactPerson() {
    }

    public ReContactPerson(Integer code, List<ContactPersonInfo> contactPersonInfos, Boolean istrue) {
        this.code = code;
        this.contactPersonInfos = contactPersonInfos;
        this.istrue = istrue;
    }
}
