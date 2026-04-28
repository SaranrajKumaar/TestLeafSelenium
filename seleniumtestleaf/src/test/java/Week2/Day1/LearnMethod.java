package Week2.Day1;

public class LearnMethod {

    void calculateSalary(int basic,int bonus,float tax) {
        float salary = basic + bonus - tax;
        System.out.println("The Salary is " + salary);
    }

    int noOfEmployees() {
        int totalEmployees = 50 +20;
        return totalEmployees;
    }

        public void getCompanyName(){
            String companyName ="TestLeaf";
            System.out.println("The Company Name is " + companyName);
        }

    public static void main(String[] args) {

        LearnMethod lm = new LearnMethod();
        lm.calculateSalary(10000, 2000, 500);
        int company =lm.noOfEmployees();
        System.out.println("The total number of employees is " + company);
        lm.getCompanyName();
    }

}
