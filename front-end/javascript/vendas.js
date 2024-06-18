// script.js

// Função para formatar a data
function formatDate(dateString) {
  return new Date(dateString);
}

// Função para atualizar os contadores na página HTML
function updateCounters(daily, monthly, yearly) {
  document.getElementById('daily-sales').textContent = daily;
  document.getElementById('monthly-sales').textContent = monthly;
  document.getElementById('yearly-sales').textContent = yearly;
}

// Função principal para buscar os dados e processá-los
async function fetchSalesData() {
  try {
    const response = await fetch('http://localhost:8080/vendas');
    const data = await response.json();

    const today = new Date();
    const currentDay = today.getDate();
    const currentMonth = today.getMonth();
    const currentYear = today.getFullYear();

    let dailySales = 0;
    let monthlySales = 0;
    let yearlySales = 0;

    data.forEach(sale => {
      const saleDate = formatDate(sale.dataVenda);
      if (saleDate.getFullYear() === currentYear) {
        yearlySales++;
        if (saleDate.getMonth() === currentMonth) {
          monthlySales++;
          if (saleDate.getDate() === currentDay) {
            dailySales++;
          }
        }
      }
    });

    console.log(monthlySales)
    updateCounters(dailySales, monthlySales, yearlySales);
  } catch (error) {
    console.error('Erro ao buscar dados das vendas:', error);
  }
}

// Chama a função para buscar e processar os dados ao carregar a página
document.addEventListener('DOMContentLoaded', fetchSalesData);
