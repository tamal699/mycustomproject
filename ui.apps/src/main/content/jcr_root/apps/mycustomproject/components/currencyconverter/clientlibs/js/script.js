document.addEventListener('DOMContentLoaded', function () {
    const btn = document.getElementById('convertBtn');
    btn.addEventListener('click', function () {
        const source = document.getElementById('sourceCurrency').value;
        const target = document.getElementById('targetCurrency').value;
        const amount = document.getElementById('amount').value;

        if (!amount || amount <= 0) {
            document.getElementById('resultArea').innerText = 'Please enter a valid amount';
            return;
        }

        fetch(`/bin/currencyConverter?source=${source}&target=${target}&amount=${amount}`)
            .then(response => response.json())
            .then(data => {
                if (data.convertedAmount) {
                    document.getElementById('resultArea').innerText = `Converted Amount: ${data.convertedAmount}`;
                } else {
                    document.getElementById('resultArea').innerText = 'Conversion failed';
                }
            })
            .catch(() => {
                document.getElementById('resultArea').innerText = 'Error converting currency';
            });
    });
});