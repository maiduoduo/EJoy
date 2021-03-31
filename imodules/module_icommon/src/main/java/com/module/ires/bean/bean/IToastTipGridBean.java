package com.module.ires.bean.bean;


/**
 * @CN:      IToastTipGridBean
 * @author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * @Date:   2021/03/31
 * @Des:   Toast扩展提示九宫格列表实体
 */
public class IToastTipGridBean {
    /**
     * 文本
     */
    private String gridTitle;
    /**
     * 英文文本
     */
    private String gridEn;
    /**
     * 是否加粗字体
     */
    private String labelText;
    /**
     * 是否展示标签
     */
    private boolean isHotLabel;


    public IToastTipGridBean(String gridTitle, String gridEn, boolean isHotLabel, String labelText) {
        this.gridTitle = gridTitle;
        this.gridEn = gridEn;
        this.isHotLabel = isHotLabel;
        this.labelText = labelText;
    }

    public IToastTipGridBean(String gridTitle, boolean isHotLabel) {
        this.gridTitle = gridTitle;
        this.isHotLabel = isHotLabel;
    }


    public String getGridEn() {
        return gridEn;
    }

    public void setGridEn(String gridEn) {
        this.gridEn = gridEn;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getGridTitle() {
        return gridTitle;
    }

    public void setGridTitle(String gridTitle) {
        this.gridTitle = gridTitle;
    }


    public boolean isHotLabel() {
        return isHotLabel;
    }

    public void setHotLabel(boolean hotLabel) {
        isHotLabel = hotLabel;
    }
}
