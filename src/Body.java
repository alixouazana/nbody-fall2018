
public class Body {
	
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
		
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	
	/**
	 * Return distance between this body and another
	 * @param b is the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow((myXPos-b.myXPos),2)+ Math.pow((myYPos -b.myYPos), 2));
		
	}
	
	/**
	 * Return force exerted on this body by another
	 * @param p is the other body that exerts force on this body
	 * @return force exerted on this body by p
	 */
	public double calcForceExertedBy(Body p) {
		double G = 6.67*1e-11;
		double r2 = Math.pow(calcDistance(p), 2);
		return G*(p.myMass)*myMass/r2;
	}
	
	/**
	 * Return force exerted in the x direction on this body by another
	 * @param p is the body that exerts force on this body
	 * @return x component of force exerted on this body by p
	 */
	public double calcForceExertedByX(Body p) {
		double F = calcForceExertedBy(p);
		double dx = p.myXPos - myXPos;
		double r = calcDistance(p);
		return F*dx/r;
	}
	
	/**
	 * Return force exerted in the y direction on this body by another
	 * @param p is the body that exerts force on this body
	 * @return y component of force exerted on this body by p
	 */
	public double calcForceExertedByY(Body p) {
		double F = calcForceExertedBy(p);
		double dy = p.myYPos - myYPos;
		double r = calcDistance(p);
		return F*dy/r;
	}
	
	/**
	 * Return total/net force exerted in the x direction on this body all other bodies
	 * @param bodies are all the bodies in the array body
	 * @return x component of net force exerted on this body by all bodies
	 */
	public double calcNetForceExertedByX(Body[] Bodies) {
		double NetForceX = 0;
		for(Body b: Bodies) {
			if(! b.equals(this)) {
				NetForceX = NetForceX + calcForceExertedByX(b);
			}
		}
		return NetForceX;
	}
	
	/**
	 * Return total/net force exerted in the y direction on this body all other bodies
	 * @param Bodies are all the bodies in the array body
	 * @return y component of net force exerted on this body by all bodies
	 */
	public double calcNetForceExertedByY(Body[] Bodies) {
		double NetForceY = 0;
		for(Body b: Bodies) {
			if(! b.equals(this)) {
				NetForceY = NetForceY + calcForceExertedByY(b);
			}
		}
		return NetForceY;
	}
	/**
	 * Mutator method: no return value but updates instance variables of body object
	 * @param deltaT is a small time step
	 * @param xforce is the x component of the net force exerted by all bodies
	 * @param yforce is the y component of the net force exerted by all bodies
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx; 
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
	
	/**
	 * Draws planets in motion
	 */
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+ myFileName);
	}
}
