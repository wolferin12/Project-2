x=0:0.1:10;
y1=2*x+3;
y2=5*x+10;
y3=10*x+1;
V= [x;y1];
Q= [x;y2];
R= [x;y3];
writematrix(V,'y1.csv');
writematrix(Q, 'y2.csv');
writematrix( R, 'y3.csv');

hold on
plot(x,y1);
plot(x,y2);
plot(x,y3);
hold off