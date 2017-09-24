import java.util.ArrayList;
import java.util.Scanner;

public class MyGradeCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Grade> grades = new ArrayList<>();
        Grade g = null;
        
        while(true) {
            String nextLine = in.nextLine();
            if (nextLine == null || nextLine.trim().length() == 0)  break;
            store(nextLine, g, grades);
        }
        double ans = -1;
        try {
            ans = calculatePoint(grades);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        
        System.out.println("credits:" + calculateCredit(grades));
        System.out.println("courses: " + grades.size());
        System.out.println("points: " + String.format("%.2f", ans));
    }
    // store the data
    private static void store(String input, Grade g, ArrayList<Grade> grades) {
        String[] nextLines = input.split(" ");
        g = new Grade();
        g.credit = Double.parseDouble(nextLines[0]);//每门课的学分
        g.score = Integer.parseInt(nextLines[1]);//每门课的成绩
        grades.add(g);
    }
    // calculate 
    private static double calculatePoint(ArrayList<Grade> grades) throws Exception {

        // 绩点
        double[] points = {	
                4.33, 4.00, 3.67, 3.33,
                3.00, 2.67, 2.33, 2.00,
                1.67, 1.33, 1.00, 0.00
        };
        // 结果
        double ans = 0;

        Grade g;
        for(Grade grade : grades) {
            g = grade;
            int s = g.score;

            //compute
            if(s < 0 || s > 100)	throw new Exception("Illegal Input!!");
            if(s >= 95) {
                ans += g.credit * points[0];
            } else if(s >= 90) {
                ans += g.credit * points[1];
            }  else if(s >= 85) {
                ans += g.credit * points[2];
            }  else if(s >= 82) {
                ans += g.credit * points[3];
            }  else if(s >= 78) {
                ans += g.credit * points[4];
            }  else if(s >= 75) {
                ans += g.credit * points[6];
            } else if(s >= 72) {
                ans += g.credit * points[7];
            } else if(s >= 68) {
                ans += g.credit * points[8];
            } else if(s >= 64) {
                ans += g.credit * points[9];
            }  else if(s >= 61) {
                ans += g.credit * points[10];
            }  else if(s == 60) {
                ans += g.credit * points[11];
            } else 
                continue;
        }
        
        double credits = calculateCredit(grades);
        ans = ans / credits;
        return ans;
    }
    // compute all the credits
    private static double calculateCredit(ArrayList<Grade> grades) {
        double ans = 0;
        for(Grade g: grades) {
            ans += g.credit;
        }
        return ans;
    }

    static class Grade {
        double credit; 
        int score;
        @Override
        public String toString() {
            return "Grade [credit=" + credit + ", score=" + score + "]";
        }
    }
}
