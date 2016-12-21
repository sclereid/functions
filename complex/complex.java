package complex;


public class complex
{
	double real;
	double imag;
	double radius;
	double phi;
	
	public double get_real()
	{
		return real;
	}
	public double get_imag()
	{
		return imag;
	}
	
	public complex(double a, double b)
	{
		real = a;
		imag = b;
		//radius = Math.sqrt(a*a+b*b);
		//phi = Math.atan(b/a);
		check();
	}
	public complex()
	{
		real = 0;
		imag = 0;
	}
	
	//public final complex i = new complex(0,1);
	
	public void setab(double a, double b)
	{
		real = a;
		imag = b;
		//radius = Math.sqrt(a*a+b*b);
		//phi = Math.atan(b/a);
		check();
	}
	public void setrp(double r, double p)
	{
		radius = r;
		phi = p;
		real = r*Math.cos(phi);
		imag = r*Math.sin(phi);
		check();
	}
	
	private void check()
	{
		final double almost_zero = 1e-10;
			real = 0;
		if(imag>-almost_zero&&imag<almost_zero)
			imag = 0;
		radius = Math.sqrt(real*real+imag*imag);
		phi = Math.atan(imag/real);
		while(phi>=Math.PI*2)
			phi -= Math.PI*2;
		while(phi<0)
			phi += Math.PI*2;
	}
	
	public String toString()
	{
		if(imag>=0)
			return ""+real+"\t+"+imag+"i";
		else
			return ""+real+"\t"+imag+"i";
	}
	public String toString2()
	{
		return ""+radius+"��"+(phi/2.0/Math.PI*360)+"��";
	}
	public boolean equals(complex z)
	{
		return (real==z.real&&imag==z.imag);
	}
	
	public complex add(complex z)
	{
		return new complex(this.real+z.real,this.imag+z.imag);
	}
	public complex sub(complex z)
	{
		return new complex(this.real-z.real,this.imag-z.imag);
	}
	public complex mul(complex z)
	{
		complex c = new complex();
		c.setrp(this.radius*z.radius, this.phi+z.phi);
		return c;
	}
	public complex div(complex z)
	{
		complex c = new complex();
		c.setrp(this.radius/z.radius, this.phi-z.phi);
		return c;
	}
	
	public complex add(double x)
	{
		return new complex(this.real+x, this.imag);
	}
	public complex sub(double x)
	{
		return new complex(this.real-x, this.imag);
	}
	public complex mul(double x)
	{
		return new complex(this.real*x, this.imag*x);
	}
	public complex div(double x)
	{
		return new complex(this.real/x, this.imag/x);
	}
	
	public static complex exp(complex z)
	{
		double tmp = Math.exp(z.real);
		return new complex(tmp*Math.cos(z.imag),tmp*Math.sin(z.imag));
	}
	public static complex expi(double x)
	{
		return new complex(Math.cos(x),Math.sin(x));
	}
}

