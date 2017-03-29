package someSearchAlgorithms.test;

import java.util.Scanner;

/**
 * Created by L on 2016/11/21.
 */
public class mydvd {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] name = new String[6];
        int[] state = new int[6];//是否借出，0是可借阅；1是以借出；
        String[] date = new String[6];//日期
        int[] count = new int[6];
        name[0] = "罗马假日";
        state[0] = 1;
        date[0] = "2016年11月3日";
        count[0] = 15;

        name[1] = "风声鹤唳";
        state[1] = 0;
        count[1] = 12;

        name[2] = "浪漫满屋";
        state[2] = 0;
        count[2] = 30;


        int fanhui = -1;//输入0返回
        int xuanxiang = 0;//选项
        do {
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
            xuanxiang = input.nextInt();
            switch (xuanxiang) {
                case 1:
                    System.out.println("-->新增");
                    System.out.println("请输入DVD名称：");
                    String dvdname = input.next();
                    int i;
                    for (i = 0; i < name.length; i++) {
                        if (name[i] == null) {
                            name[i] = dvdname;
                            date[i] = "";
                            System.out.println("新增《" + name[i] + "》成功");
                            break;
                        }
                    }
                    if (i == name.length - 1) {
                        System.out.println("货架已满");

                    }
                    System.out.println("**********************");
                    break;
                case 2://查看
                    System.out.println("-->查看");
                    System.out.println("序号\t状态\t名称\t借出日期\t借出次数");
                    for (int a = 0; a < name.length; a++) {
                        if (name[a] != null) {
                            String zhuangtai = (state[a] == 0) ? "可借阅" : "以借出";
                            System.out.println((a + 1) + "\t" + zhuangtai + "\t" + date + "\t" + count);
                        }
                    }
                    System.out.println("**********************");
                    break;
                case 3://删除
                    System.out.println("-->删除");
                    System.out.println("请输入dvd名称：");
                    String delete = input.next();
                    int index = -1;
                    boolean temp = false;
                    for (int a = 0; a < name.length; a++) {
                        if (name[a] != null && delete.equals(name[a])) {
                            if (state[a] == 0) {
                                index = a;
                                temp = true;
                                System.out.println("删除《" + name[a] + "》成功！");
                                break;
                            } else {
                                System.out.println("已被借出，无法删除！");
                                temp = true;
//                            break;
                            }
                        }
                    }
                    if (!temp) {
                        System.out.println("没有找到该影片");
                    }
                    if (index != -1) {
//                        for (int j = index; j < name.MaxSize - 1; j++) {
//                            name[j] = name[j + 1];
//                            state[j] = state[j + 1];
//                            date[j] = date[j + 1];
//                            count[j] = count[j + 1];
//                        }
                        System.arraycopy(name, index, name, index + 1, name.length - index - 1);
                        System.arraycopy(state, index, state, index + 1, state.length - index - 1);
                        System.arraycopy(date, index, date, index + 1, date.length - index - 1);
                        System.arraycopy(count, index, count, index + 1, count.length - index - 1);
                        name[name.length - 1] = null;
//                        state[name.MaxSize - 1] = 0;
                        date[name.length - 1] = null;
//                        count[name.MaxSize - 1] = 0;
                    }
                    System.out.println("**********************");


                    break;
                case 4://借出

                    System.out.println("**********************");
                    break;
                case 5://归还

                    System.out.println("**********************");
                    break;
                case 6:
                    break;
                default:
                    System.out.println("请重新输入");
                    fanhui = 0;
                    break;
            }
            if (fanhui != 6) {
                System.out.println("输入0返回：");
                fanhui = input.nextInt();
            } else {
                break;
            }
        } while (fanhui == 0);
    }
}
