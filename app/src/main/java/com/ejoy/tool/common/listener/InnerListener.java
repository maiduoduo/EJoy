package com.ejoy.tool.common.listener;


import com.ejoy.tool.common.db.CitysBean;

public interface InnerListener {
    void dismiss(int position, CitysBean data);
    void locate();
}
