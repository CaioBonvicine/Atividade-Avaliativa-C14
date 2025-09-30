cd bilioteca
sudo apt-get install mailutils
echo "Sending e-mail after pipleline completion" | mail -s "Pipeline" "$EMAIL_RECIPIENT"
