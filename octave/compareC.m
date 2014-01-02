function compareC(s,m,n) 
% s individual bin size
% m number of bins
% n number of trials

a = rand(s,m*n) > 0.5;
irand = [0:m:(m*n-1)] + ceil(rand(1,n)*m);
subplot(1,3,1);
hist(mean(a(:,irand)),30);
title('Vrand');

ifirst = [0:m:(m*n-1)] + ones(1,n);
subplot(1,3,2);
hist(mean(a(:,ifirst)),30);
title('Vfirst');

b = mean(a);
c = reshape(b,m,n);
vmins = min(c);
subplot(1,3,3);
hist(vmins,30);
title('Vmin');




