/*********************************************
 * OPL 12.7.1.0 Model
 * Author: bryan
 * Creation Date: 17 mars 2023 at 12:26:38
 *********************************************/
int n = 7;

int s = 1;

int t = 7;

range sn = 1..n;

range tn = 1..n;

float poids[sn][tn]=[
[10000,3.0,7.2,10000,10000,10000,10000],
[10000,10000,10000,10000,10000,10000,10000],
[10000,10000,10000,5.1,10000,10000,10000],
[10000,10000,10000,10000,10000,10000,2.5],
[4.1,10000,10000,10000,10000,7.8,10000],
[10000,10000,10000,10000,10000,10000,10000],
[10000,10000,10000,10000,10000,4.5,10000]
];

dvar boolean x[sn][tn];

minimize sum(i in sn, j in tn) poids[i][j] * x[i][j];

subject to {
forall(i in sn) 
	if(i == s){
		sum(j in tn) x[i][j] - sum(j in tn) x[j][i] == 1;
	}else if(i == t){
		sum(j in tn) x[i][j] - sum(j in tn) x[j][i] == -1;
	}else{
		sum(j in tn) x[i][j] - sum(j in tn) x[j][i] == 0;
	}

}

execute DISPLAY{
	for(var i in sn){
		for(var j in tn){
			if(x[i][j] == 1 && j!=t){
				write(i+" ")
			}else if(x[i][j] == 1){
				writeln(i+" "+j)		
			}
		}	
	}
	write(cplex.getObjValue())
}