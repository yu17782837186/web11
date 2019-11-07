package cn.com.ning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.*;

public class NumServlet extends HttpServlet {

//    //重写init方法，将数据读取到ServletContext对象中
//    @Override
//    public void init() throws ServletException {
//        //获取文件中的计数器
//        String path = this.getServletContext().getRealPath("/nums/nums.txt");
//        //声明流对象
//        FileReader fr = null;
//        BufferedReader br = null;
//        try {
//            fr = new FileReader(path);
//            br = new BufferedReader(fr);
//            String nums = br.readLine();
//            System.out.println(nums);
//            this.getServletContext().setAttribute("nums",nums);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                if (br != null){
//                    br.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (fr != null){
//                    fr.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //重写销毁方法，存储计时器到文件中
//    @Override
//    public void destroy() {
//        //获取网页计数器
//        int nums = (int)this.getServletContext().getAttribute("nums");
//        //获取文件路径
//        String path = this.getServletContext().getRealPath("/nums/nums.txt");
//        //声明流对象
//        BufferedWriter bw = null;
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(path);
//            bw = new BufferedWriter(fw);
//            bw.write(nums+"");
//            bw.flush();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                if (fw != null) {
//                    fw.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (bw != null){
//                    bw.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//覆写init初始化方法，将数据读取到ServletContext对象中
@Override
public void init() throws ServletException {
    //获取文件中的计数器数据
    //获取文件路径
    String path=this.getServletContext().getRealPath("/nums/nums.txt");
    //声明流对象
    FileReader fr=null;
    BufferedReader br=null;
    try {
        fr=new FileReader(path);
        br=new BufferedReader(fr);
        String nums=br.readLine();
        System.out.println(nums);
        this.getServletContext().setAttribute("nums", nums);
    } catch (Exception e) {
        e.printStackTrace();
    }finally{
        try {
            fr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

    //覆写销毁方法，存储计数器到文件中
    @Override
    public void destroy() {
        //获取网页计数器
        int nums=(int) this.getServletContext().getAttribute("nums");
        //获取文件路径
        String path=this.getServletContext().getRealPath("/nums/nums.txt");
        //声明流对象
        BufferedWriter bw=null;
        FileWriter fw=null;
        try {
            fw=new FileWriter(path);
            bw=new BufferedWriter(fw);
            bw.write(nums+"");
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
