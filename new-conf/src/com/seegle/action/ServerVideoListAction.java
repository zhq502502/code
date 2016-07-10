package com.seegle.action;

import com.seegle.data.FileOperation;
import com.seegle.data.GetConfigFile;
import com.seegle.form.VideoInfoActionForm;
import com.seegle.util.PropUtil;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *服务器录像文件列表
 */
public class ServerVideoListAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FileOperation fo = new FileOperation();
        GetConfigFile gcf = new GetConfigFile();
        int firstIndex = 0;  //显示记录的起始位置
        int showNumber = Integer.parseInt(gcf.getConfig("SHOW_NUMBER"));  //每页显示记录数
        int queryNumber = 1;  //查询页数
        int nowPage = 1;  //当前页
        String page = request.getParameter("page");//所要查询的页数
        if (page != null) {//如果用户选择页数
            queryNumber = Integer.parseInt(page);
            if (queryNumber > 1) {  //如果查询的页数大于1
                nowPage = queryNumber;
                firstIndex = showNumber * queryNumber - showNumber;  //显示记录的起始位置
            }
        }

        ArrayList vList = new ArrayList();
        String filepath = getServlet().getServletContext().getRealPath("upload/server_upload")+"/";
        vList = fo.fileList(filepath, firstIndex, showNumber);
        request.setAttribute("videoList", vList);
        if (vList.size() > 0) {
            VideoInfoActionForm vif = (VideoInfoActionForm) vList.get(0);
            request.setAttribute("nowPage", nowPage);  //当前页
            request.setAttribute("pageNumber", vif.getPageNumber());//分页数
        } else {
            request.setAttribute("nowPage", 1);  //当前页
            request.setAttribute("pageNumber", 1);//分页数
        }
        request.setAttribute("client_registry", PropUtil.getInstance().getValue("client.registry"));
        request.setAttribute("client_confname", PropUtil.getInstance().getValue("client.confname"));
        request.setAttribute("client_sgplayname", PropUtil.getInstance().getValue("client.sgplayname"));
        return mapping.findForward(SUCCESS);
    }
}

