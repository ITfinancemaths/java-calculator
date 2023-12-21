import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener{ 
    //上部组件初始化
    private JPanel jp=new JPanel();//设置中间层容器，用于集成文本输入框和清除按键
    private JTextField jtf=new JTextField();//设置文本框
    private JButton jb=new JButton("C");//设置清除按键
    //中部组件初始化
    private JPanel jpa=new JPanel();//设置中间层容器

    public Calculator() throws HeadlessException{
        this.init();
        this.North();
        this.Center();
    }

    public void init(){//配置顶层容器
        this.setTitle("简易计算器");//起标题
        this.setSize(500,500);//尺寸
        this.setLayout(new BorderLayout());//设置边框布局管理器，选用组件间无间隙方式
        this.setResizable(false);//此行限制了边框，使其不能拉伸，也可修改
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击“关闭”键时触发的操作
        this.setLocationRelativeTo(null);//使界面默认出现在屏幕的中心位置
        
    }

    public void North(){//上部配置
        
        this.jtf.setPreferredSize(new Dimension(250,50));//设置文本框长宽
        this.jb.setPreferredSize(new Dimension(50,50));//设置按键长宽
        this.add(jp,BorderLayout.NORTH);//将中间层容器布局在顶层容器的上部
        jp.add(jtf);//将文本框添加到中间层容器上
        this.jb.setForeground(Color.RED);//设置按键上标签文本的颜色
        jp.add(jb);//将按键添加到中间层容器上
        jb.addActionListener(new ActionListener(){//为按键设置动作监听器，执行清空文本框的操作
            public void actionPerformed(ActionEvent e){
                jtf.setText("");
            }
        });
    }

    public void Center(){//中部配置
        this.jpa.setLayout(new GridLayout(4,4));//设置4*4网格布局管理器
        String text="123+456-789*0.=/";//按键上的文本
        for(int i=0;i<16;i++){//放置按键
            JButton jb=new JButton();
            String temp=text.substring(i,i+1);//每次取一个字符
            jb.setText(temp);
            jb.setFont(new Font("黑体",Font.BOLD,20));//设置文本字型，大小
            jb.addActionListener(this);//在按键上添加动作监听器
            jpa.add(jb);//将按键添加到网格布局管理器上
        }
        this.add(jpa,BorderLayout.CENTER);//将中间层容器布局在顶层容器的中部
    }

    private String tempstr=null;
    private String operator=null; 

    public void actionPerformed(ActionEvent e){
        String str=e.getActionCommand();//得到点击的按键上的文本
        if(".0123456789".indexOf(str)!=-1){//如果文本是数字或小数点
            this.jtf.setText(jtf.getText()+str);//将其拼接在当前文本之后
        }else if(str.matches("[\\+\\-*/]{1}")){//如果文本是四个运算符之一
            operator=str;//记录运算符
            tempstr=this.jtf.getText();//记录当前文本
            this.jtf.setText("");//清空文本框
        }else{
            Double a=Double.valueOf(tempstr);//借助泛型保存之前的文本内容与现在的文本内容
            Double b=Double.valueOf(this.jtf.getText());
            Double result=null;
            switch(operator){//为对应的运算符设计运算操作
                case "+":
                result=a+b;
                break;
                case "-":
                result=a-b;
                break;
                case "*":
                result=a*b;
                break;
                case "/":
                if(b!=0){
                    result=a/b;
                }
                else{
                    this.jtf.setText("0不能做除数");
                }
                break;
            }
            this.jtf.setText(result.toString());//将运算结果以字符串形式显示在文本框中
        }
    }


    public static void main(String[] args){
        Calculator cal=new Calculator();
        cal.setVisible(true);//窗口可见
    }
}
