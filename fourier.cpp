#include <iostream>
#include <cmath>

#define dprint(s) std::cout << #s << " = " << s << '\n';

using namespace std;

class integral_base
{
protected:
	const double delta = 0.00001;
public:
	double T = 2;
	//virtual double f(double x){}
	virtual double operator()(double x){}
	
	double integral(double a, double b)
	{
		double ans = 0;
		while((a+=delta)<b)
		{
			ans += (*this)(a)*delta;
		}
		return ans;
	}
	
	double intsin(double n)
	{
		double ans = 0;
		for(double x = 0; x < T; x+=delta)
		{
			ans += (*this)(x)*sin(n*x)*delta;
		}
		return ans;
	}
	
	double intcos(double n)
	{
		double ans = 0;
		for(double x = 0; x < T; x+=delta)
		{
			ans += (*this)(x)*cos(n*x)*delta;
		}
		return ans;
	}
};

class :public integral_base
{
public:
	double operator()(double x)
	{
		if((int)x%2)
			return 1;
		else
			return -1;
	}
} f1;

int main()
{
	//integral_base<double> b;
	/*
	dprint(f1(0.5));
	dprint(f1(1));
	dprint(f1.integral(0,1.2));
	*/
	for(int i = 0; i < 10; i++)
	{
		cout << " +Math.sin(" << i << "*x)*" << f1.intsin(i) << " +Math.sin(" << i << "*x)*" << f1.intsin(i) << ' ';
		//cout << i << '\t' << f1.intsin(i) << '\t' << f1.intcos(i) << '\n';
	}
	return 0;
}
