now=$(date)
cd /home/swg/swg-main/
cd data
git add .
git commit -m "GodClient Sync $now"
git push origin master
echo "Pushed to data. Refresh godclient!"
