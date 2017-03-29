package someSearchAlgorithms.test;

/**
 * Created by L on 2016/11/21.
 */

import java.util.Scanner;

public class dvd {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        //是否退出系统,false代表退出系统
        boolean flag=true;
        //DVD相关信息,最多只能存储6个DVD信息
        String[] name=new String[6]; //DVD名称
        int[] state=new int[6]; //借阅状态，0：可借阅 ；1：已借出
        int[] date=new int[6];  //借出日期
        int[] count=new int[6]; //借出次数

		/*
		 * 初始化三个DVD
		 * */
        name[0]="罗马假日";
        state[0]=1;
        date[0]=1;
        count[0]=15;

        name[1]="风声鹤唳";
        state[1]=0;
        count[1]=12;

        name[2]="浪漫满屋";
        state[2]=0;
        count[2]=30;

        int num=-1;
        //循环操作
        do{
            //输出欢迎菜单
            System.out.println("欢迎使用迷你DVD管理器");
            System.out.println("-------------------------");
            System.out.println("1.新增DVD");
            System.out.println("2.查看DVD");
            System.out.println("3.删除DVD");
            System.out.println("4.借出DVD");
            System.out.println("5.归还DVD");
            System.out.println("6.退出DVD");
            System.out.println("-------------------------");
            System.out.println("请选择：");
            int choose=input.nextInt();
            switch(choose){
                case 1:
                    //新增DVD
                    System.out.println("-->新增DVD\n");
                    System.out.println("请输入DVD名称：");
                    String dvdName=input.next();
                    boolean flag2=false;
                    for(int i=0;i<name.length;i++){
                        if(name[i]==null){
                            name[i]=dvdName;
                            System.out.println("新增《"+dvdName+"》成功！");
                            flag2=true;
                            break;
                        }
                    }
                    if(!flag2){
                        System.out.println("抱歉,新增《"+dvdName+"》失败！货架已满！");
                    }
                    System.out.println("*****************************************");
                    break;
                case 2:
                    //查看DVD
                    System.out.println("-->查看DVD\n");
                    System.out.println("序号\t状态\t名称\t借出日期\t借出次数");
                    for(int i=0;i<name.length;i++){
                        if(name[i]!=null){
                            String dvdState=(state[i]==0)?"可借阅":"已借出";
                            String dateStr=(date[i]==0)?"":(date[i]+"日");
                            System.out.println((i+1)+"\t"+dvdState+"\t"+name[i]+"\t"+dateStr+"\t"+count[i]+"次");
                        }
                    }
                    System.out.println("*****************************************");
                    break;
                case 3:
                    //删除DVD
                    System.out.println("-->删除DVD\n");
                    System.out.println("请输入要删除DVD的名称：");
                    String delete=input.next();
                    boolean flag3=false;
                    int index=-1;  //记录要删除DVD的位置
                    for(int i=0;i<name.length;i++){
                        if(name[i]!=null && delete.equals(name[i]) && state[i]==0){
                            flag3=true;
                            index=i;
                            System.out.println("DVD删除成功！");
                            break;
                        }else if(name[i]!=null && delete.equals(name[i]) && state[i]==1){
                            flag3=true;
                            System.out.println("该DVD已被借出，目前无法删除！");
                            break;
                        }
                    }
                    //根据记录下来的删除DVD的位置进行删除工作
                    if(index!=-1){
                        for(int j=index;j<name.length;j++){
                            if(j!=name.length-1){
                                name[j]=name[j+1];
                                state[j]=state[j+1];
                                date[j]=date[j+1];
                            }
                            name[name.length-1]=null;

                        }
                    }


                    if(!flag3){
                        System.out.println("没有找到匹配信息!");
                    }
                    System.out.println("*****************************************");
                    break;
                case 4:
                    //借出DVD
                    System.out.println("-->借出DVD\n");
                    System.out.print("请输入DVD名称：");
                    String want=input.next();  //要借出的DVD名称
                    for(int i=0;i<name.length;i++){
                        if(name[i]==null){  //无匹配
                            System.out.println("没有找到匹配信息！");
                            break;
                        }else if(want.equals(name[i]) && state[i]==0){  //找到匹配可借
                            state[i]=1; //将此DVD置于借出状态
                            System.out.print("请输入借出日期：");
                            date[i]=input.nextInt();
                            while(date[i]<1 || date[i]>31){  //当输入借出的日期不满足1-31时
                                System.out.println("必须输入大于等于1且小于等于31的数字，请重新输入：");
                                date[i]=input.nextInt();
                            }
                            System.out.println("借出《"+want+"》成功！");
                            count[i]++;
                            break;
                        }else if(want.equals(name[i]) && state[i]==1){  //找到匹配已被借出
                            System.out.println("《"+want+"》已被借出！");
                            break;
                        }
                    }
                    System.out.println("*****************************************");
                    break;
                case 5:
                    //归还DVD
                    System.out.println("-->归还DVD\n");
                    int charge=0;  //租金
                    System.out.print("请输入归还DVD名称：");
                    want=input.next();
                    for(int i=0;i<name.length;i++){
                        if(name[i]==null){ //无匹配
                            System.out.println("没有找到匹配信息！");
                            break;
                        }else if(want.equals(name[i]) && state[i]==1){//找到匹配
                            state[i]=0;  //将借阅状态修改为可借阅
                            System.out.print("请输入归还日期：");
                            int redate=input.nextInt();
                            while(redate<date[i] || redate>31){  //归还日期不能小于借出日期，也不能大于31
                                if(redate<date[i]){
                                    System.out.println("归还日期不能小于借出日期，请重新输入：");
                                }else{
                                    System.out.println("一个月只有31天，请重新输入：");
                                }
                                redate=input.nextInt();
                            }
                            charge=redate-date[i];
                            System.out.println("\n归还《"+want+"》成功！");
                            System.out.println("借出日期为："+date[i]+"日");
                            System.out.println("归还日期为："+redate+"日");
                            System.out.println("应付租金（元）："+charge);
                            break;
                        }else if(want.equals(name[i]) && state[i]==0){//找到匹配但没有借出
                            System.out.println("该DVD没有被借出!无法进行归还操作。");
                            break;
                        }
                    }
                    System.out.println("*****************************************");
                    break;
                case 6:
                    //退出DVD
                    flag=false;
                    break;
                default:
                    flag=false;
                    break;
            }
            if(flag){
                System.out.println("输入0返回：");
                num=input.nextInt();
            }else{
                break;
            }
        }while(num==0);
        System.out.println("谢谢使用！");
    }
}


