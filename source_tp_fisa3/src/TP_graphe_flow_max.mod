/*********************************************
 * OPL 12.7.1.0 Model
 * Author: bryan
 * Creation Date: 14 avr. 2023 at 10:01:20
 *********************************************/
int n = ...;

int s = ...;

int t = ...;

range sn = 1..n;

range tn = 1..n;

float poids[sn][tn]=...;

dvar float f[sn][tn];


maximize sum(j in tn) f[s][j];

subject to {
forall(i in sn,j in tn){ 
	if(poids[i][j]!=10000){
		f[i][j] <= poids[i][j];
	}else{
		f[i][j] == 0;
	}
	f[i][j]>=0;
}	

forall(i in sn)
  if(i != s && i!=t){
      sum(j in tn) f[i][j] == sum(j in tn) f[j][i];
  }
}

execute DISPLAY{

	writeln(f+"\n");

	write(cplex.getObjValue());
}