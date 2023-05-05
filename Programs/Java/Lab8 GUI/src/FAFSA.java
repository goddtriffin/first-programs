/**
 * The FAFSA class keeps track of information about a student and uses it to 
 * determine the student's eligibility for federal financial aid and the total
 * estimated amount of aid awarded to those who are eligible.
 * 
 * @author Neil Allison, nalliso@purdue.edu
 *
 */
public class FAFSA {
	// Three basic requirements
	boolean isAcceptedStudent;
	boolean isSSregistered;
	boolean hasSSN;
	
	// One of four required
	boolean hasValidResidency;
	
	// Student demographics
	boolean isDependent;
	int age;
	int creditHours;
	double studentIncome;
	double parentIncome;
	String classStanding;

	/**
	 * Constructor. Initializes the member variables for this student.
	 * 
	 * @param isAcceptedStudent The student is accepted into higher education
	 * @param isSSregistered The student is registered for selective service
	 * @param hasSSN The student has a social security number
	 * @param hasValidResidency The student has valid residency status
	 * @param age The student's age
	 * @param creditHours Number of credit hours the student is taking
	 * @param studentIncome The student's income
	 * @param parentIncome The student's parents' income
	 * @param classStanding The student's current class standing
	 */
	public FAFSA(boolean isAcceptedStudent, boolean isSSregistered, 
			boolean hasSSN, boolean hasValidResidency, boolean isDependent, 
			int age, int creditHours, double studentIncome, double parentIncome,
			String classStanding) {
		this.isAcceptedStudent = isAcceptedStudent;
		this.isSSregistered = isSSregistered;
		this.hasSSN = hasSSN;
		this.hasValidResidency = hasValidResidency;
		this.isDependent = isDependent;
		this.age = age;
		this.creditHours = creditHours;
		this.studentIncome = studentIncome;
		this.parentIncome = parentIncome;
		this.classStanding = classStanding;
	}
	
	/**
	 * Calculates the students expected family contribution. If the student is
	 * a dependent, then their EFC is calculated by the sum of the students 
	 * income and their parents income, else if it just the students income.
	 * 
	 * @return The students expected family contribution
	 */
	public double calcEFC() {
		return isDependent ? studentIncome + parentIncome : studentIncome;
	}
	
	/**
	 * Calculates the student's total Stafford loan award. The Stafford loan is 
	 * only available for students having 9 or more credit hours. The amount 
	 * of the award is calculated by the students dependency status and which
	 * year they are in school. For dependents, the awards are:
	 * Undergraduate: 5000
	 * Graduate: 10000
	 * 
	 * For independents:
	 * Undergraduate: 10000
	 * Graduate: 20000
	 * 
	 * @return The student's calculated Stafford loan award amount
	 */
	public double calcStaffordLoan() {
		if (classStanding == null) {
			return 0;
		}
		
		if (creditHours < 9) {
			return 0;
		}
		
		if (isDependent) {
			if (classStanding.equalsIgnoreCase("UNDERGRADUATE")) {
				return 5000;
			} else if (classStanding.equalsIgnoreCase("GRADUATE")){
				return 10000;
			} else {
				return 0;
			}
		} else {
			if (classStanding.equalsIgnoreCase("UNDERGRADUATE")) {
				return 10000;
			} else if (classStanding.equalsIgnoreCase("GRADUATE")){
				return 20000;
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Calculates the student's federal grant award. The students EFC must be
	 * less than or equal to 50000 and they must be an undergraduate. If they
	 * have less than 9 credit hours, the award is 2500 and if they have 9 or
	 * more credit hours, the award is 5775;
	 * 
	 * @return The student's calculated federal grant award amount
	 */
	public double calcFederalGrant() {
		if (classStanding == null) {
			return 0;
		}
		
		if (calcEFC() > 50000) {
			return 0;
		}
		
		if (classStanding.equalsIgnoreCase("UNDERGRADUATE")) {
			if (creditHours < 9) {
				return 2500;
			} else {
				return 5775;
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * Calculates the student's work study award. The work study award is
	 * based on the student's EFC, broken down as follows:
	 * 0 - 30,000: 1500
	 * 30,000.01 - 40,000: 1000
	 * 40,000.01 - 50,000: 500
	 * > 50,000: 0
	 * 
	 * @return The student's calculated federal grant award amount
	 */
	public double calcWorkStudy() {
		if (calcEFC() > 50000) {
			return 0;
		} else if (calcEFC() > 40000) {
			return 500;
		} else if (calcEFC() > 30000) {
			return 1000;
		} else {
			return 1500;
		}
	}
	
	/**
	 * Calculates the student's total combined federal aid award. The total
	 * aid award is calculated as the sum of Stafford loan award, federal grant
	 * award, and work study award.
	 * 
	 * @return The student's total aid award amount
	 */
	public double calcFederalAidAmount() {
		if (!isFederalAidEligible()) {
			return 0;
		}
		
		double stafford = calcStaffordLoan();
		double grants = calcFederalGrant();
		double workStudy = calcWorkStudy();
		
		return stafford + grants + workStudy;
	}
	
	/**
	 * Determines if the student is eligible for federal financial aid. To be 
	 * eligible, the student must be an accepted student to a higher education
	 * program (isAcceptedStudent), must be registered with the selective service
	 * (isSSregistered) if they are between  the ages of 18-25 inclusive, must
	 * have a social security number (hasSSN), and must have valid residency 
	 * status (hasValidResidency).
	 * 
	 * @return True if the student is aid eligible and false otherwise
	 */
	public boolean isFederalAidEligible() {
		if ((age >= 18 && age <= 25) && !isSSregistered) {
			return false;
		}
		
		if (isAcceptedStudent && hasSSN) {
			if (hasValidResidency) {
				return true;
			}
		}
		
		return false;
	}
}
