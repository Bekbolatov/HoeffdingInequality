function compareCLessMem(s,m,n) 
% s individual bin size
% m number of bins
% n number of trials

bins = 50;
vrand = zeros(n,1);
vone = zeros(n,1);
vmin = zeros(n,1);


for i = 1:n
    a = rand(s,m) > 0.5;
    irand = ceil(rand(1,1)*m);
    vrand(i,1) = mean(a(:,irand));
    vone(i,1) = mean(a(:,1));
    vmin(i,1) = min(mean(a));
endfor
subplot(1,3,1);
hist(vrand, bins);
title('Vrand');

subplot(1,3,2);
hist(vone, bins);
title('Vfirst');

subplot(1,3,3);
hist(vmin, bins);
title('Vmin');

