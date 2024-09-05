    document.addEventListener('DOMContentLoaded', function () {

        document.getElementById('make').addEventListener('change', function () {
            fetchModels(this.value);
            resetDropdowns();
        });

        document.getElementById('model').addEventListener('change', function () {
            fetchProductCodes(this.value);
            fetchPriceAndStock();
        });

        document.getElementById('productcode').addEventListener('change', fetchPriceAndStock);

        document.getElementById('quantity').addEventListener('input', calculateTotalPrice);

        function resetDropdowns() {
            document.getElementById('model').innerHTML = '<option value="" selected>Select Model</option>';
            document.getElementById('productcode').innerHTML = '<option value="" selected>Select Product Code</option>';
            document.getElementById('ratePerItem').value = '';
            document.getElementById('stockInHand').value = '';
        }

        function fetchModels(make) {
            if (make) {
                fetch(`/api/order/getModel/${make}`)
                    .then(response => response.json())
                    .then(models => {
                        let modelSelect = document.getElementById('model');
                        modelSelect.innerHTML = '<option value="" selected>Select Model</option>';
                        models.forEach(model => {
                            let option = document.createElement('option');
                            option.value = model;
                            option.text = model;
                            modelSelect.appendChild(option);
                        });
                    })
                    .catch(error => console.error('Error fetching models:', error));
            } else {
                resetDropdowns();
            }
        }

        function fetchProductCodes(model) {
            const make = document.getElementById('make').value;
            if (make && model) {
                fetch(`/api/order/getProductCode/${make}/${model}`)
                    .then(response => response.json())
                    .then(productCodes => {
                        let productCodeSelect = document.getElementById('productcode');
                        productCodeSelect.innerHTML = '<option value="" selected>Select Product Code</option>';
                        productCodes.forEach(productCode => {
                            let option = document.createElement('option');
                            option.value = productCode;
                            option.text = productCode;
                            productCodeSelect.appendChild(option);
                        });
                    })
                    .catch(error => console.error('Error fetching product codes:', error));
            }
        }

        function fetchPriceAndStock() {
            const make = document.getElementById('make').value;
            const model = document.getElementById('model').value;
            const productCode = document.getElementById('productcode').value;
            if (make && model && productCode) {
                fetch(`/api/order/getPriceAndStock/${make}/${model}/${productCode}`)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById('ratePerItem').value = data.ratePerItem;
                        document.getElementById('stockInHand').value = data.stockInHand;
                        calculateTotalPrice();
                    })
                    .catch(error => console.error('Error fetching price and stock:', error));
            }
        }

        function calculateTotalPrice() {
            const ratePerItem = parseFloat(document.getElementById('ratePerItem').value) || 0;
            const quantity = parseInt(document.getElementById('quantity').value, 10) || 0;
            const totalPrice = ratePerItem * quantity;
            document.getElementById('totalPrice').value = totalPrice.toFixed(2);
        }

        window.submitOrder = function() {
            const quantity = document.getElementById('quantity').value;
            const totalPrice = document.getElementById('totalPrice').value;

            document.getElementById('hiddenQuantity').value = quantity;
            document.getElementById('hiddenTotalPrice').value = totalPrice;
            document.getElementById('orderForm').submit();
        }
    });

function updateTime() {
  const now = new Date();
  const time = now.toLocaleTimeString();
  const date = now.toLocaleDateString();
  document.getElementById("current-time").textContent = time;
  document.getElementById("current-date").textContent = date;
}

updateTime();
setInterval(updateTime, 1000);


