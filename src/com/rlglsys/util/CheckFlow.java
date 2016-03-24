package com.rlglsys.util;

import java.util.HashMap;
import java.util.Map;

public class CheckFlow {
    // 审核流程取得 （个人审核）
    public Map<String, String> getPersonalFlow(String ednMark,String scale,String nowMark,String result) {
        String flow="";
        String stutas="";
        // 市局直管
        if ("03".equals(scale)) {
            if ("01".equals(ednMark)) {
                flow = "提交申请->单位";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,审核完成";
                    }
                }
            } else if ("04".equals(ednMark)) {
                flow = "提交申请->单位->市局->省厅";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else if ("01".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,等待市局审核";
                    }
                } else if ("02".equals(nowMark) || "03".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else {
                        stutas = "市局审核通过,等待省厅审核";
                    }
                } else if ("04".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "省厅审核驳回";
                    } else {
                        stutas = "省厅审核通过,审核完成";
                    }
                }
            } else {
                flow = "提交申请->单位->市局";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else if ("01".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,等待市局审核";
                    }
                } else {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else {
                        stutas = "市局审核通过,审核完成";
                    }
                }
            }
        // 省厅直管
        } else if ("04".equals(scale)) {
            if ("01".equals(ednMark)) {
                flow = "提交申请->单位";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,审核完成";
                    }
                }
            } else {
                flow = "提交申请->单位->省厅";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else if ("01".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,等待省厅审核";
                    }
                } else {
                    if ("003".equals(result)) {
                        stutas = "省厅审核驳回";
                    } else {
                        stutas = "省厅审核通过,审核完成";
                    }
                }
            }
        } else {
        // 乡镇直管 区县直管
            if ("01".equals(ednMark)) {
                flow = "提交申请->单位";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,审核完成";
                    }
                }
            } else if ("02".equals(ednMark)) {
                flow = "提交申请->单位->区局";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else if ("01".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,等待区局审核";
                    }
                } else {
                    if ("003".equals(result)) {
                        stutas = "区局审核驳回";
                    } else {
                        stutas = "区局审核通过,审核完成";
                    }
                }
            } else if ("03".equals(ednMark)) {
                flow = "提交申请->单位->区局->市局";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else if ("01".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,等待区局审核";
                    }
                } else if ("02".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "区局审核驳回";
                    } else {
                        stutas = "区局审核通过,等待市局审核";
                    }
                } else if ("03".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else {
                        stutas = "市局审核通过,审核完成";
                    }
                }
            } else if ("04".equals(ednMark)) {
                flow = "提交申请->单位->区局->市局->省厅";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待单位审核";
                } else if ("01".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "单位审核驳回";
                    } else {
                        stutas = "单位审核通过,等待区局审核";
                    }
                } else if ("02".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "区局审核驳回";
                    } else {
                        stutas = "区局审核通过,等待市局审核";
                    }
                } else if ("03".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else {
                        stutas = "市局审核通过,等待省厅审核";
                    }
                } else if ("04".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "省厅审核驳回";
                    } else {
                        stutas = "省厅审核通过,审核完成";
                    }
                }
            }
        }
        Map<String,String> checkInfoMap = new HashMap<String,String>();
        checkInfoMap.put("flow", flow);
        checkInfoMap.put("stutas", stutas);
        return checkInfoMap;
    }
    

    // 审核流程取得 （单位审核）
    public Map<String, String> getUnitFlow(String ednMark,String scale,String nowMark,String result) {
        String flow="";
        String stutas="";
        // 市局直管
        if ("03".equals(scale)) {
            if ("04".equals(ednMark)) {
                flow = "提交申请->市局->省厅";
                // 提交完成
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待市局审核";
                } else if ("01".equals(nowMark) || "02".equals(nowMark) || "03".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "市局审核通过,等待省厅审核";
                    }
                } else if ("04".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "省厅审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "省厅审核通过,审核完成";
                    }
                }
            } else {
                flow = "提交申请->市局";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待市局审核";
                } else {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "市局审核通过,审核完成";
                    }
                }
            }
        // 省厅直管
        } else if ("04".equals(scale)) {
            flow = "提交申请->省厅";
            if ("00".equals(nowMark)) {
                stutas = "提交完成,等待省厅审核";
            } else {
                if ("003".equals(result)) {
                    stutas = "省厅审核驳回";
                } else if("004".equals(result)){
                    stutas = "需要补充资料";
                } else {
                    stutas = "省厅审核通过,审核完成";
                }
            }
        } else {
        // 乡镇直管 区县直管
            if ("03".equals(ednMark)) {
                flow = "提交申请->区局->市局";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待区局审核";
                } else if ("01".equals(nowMark) || "02".equals(nowMark) || "04".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "区局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "区局审核通过,等待市局审核";
                    }
                } else if ("03".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "市局审核通过,审核完成";
                    }
                }
            } else if ("04".equals(ednMark)) {
                flow = "提交申请->区局->市局->省厅";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待区局审核";
                } else if ("01".equals(nowMark) || "02".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "区局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "区局审核通过,等待市局审核";
                    }
                } else if ("03".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "市局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "市局审核通过,等待省厅审核";
                    }
                } else if ("04".equals(nowMark)) {
                    if ("003".equals(result)) {
                        stutas = "省厅审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "省厅审核通过,审核完成";
                    }
                }
            } else {
                flow = "提交申请->区局";
                if ("00".equals(nowMark)) {
                    stutas = "提交完成,等待区局审核";
                } else {
                    if ("003".equals(result)) {
                        stutas = "区局审核驳回";
                    } else if("004".equals(result)){
                        stutas = "需要补充资料";
                    } else {
                        stutas = "区局审核通过,审核完成";
                    }
                }
            }
        }
        Map<String,String> checkInfoMap = new HashMap<String,String>();
        checkInfoMap.put("flow", flow);
        checkInfoMap.put("stutas", stutas);
        return checkInfoMap;
    }
}
