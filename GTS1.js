class TourResult {
    constructor(tour, detailTour , cost) {
        this.tour = tour;
        this.cost = cost;
        this.detailTour = detailTour;
    }
}

function findTour(n, u, c) {
    let visited = new Array(n).fill(false);
    let u_c = u - 1; // Vì chỉ số trong mảng c bắt đầu từ 0 
                     // nên đỉnh lùi lại 1 đơn vị
    let v = u_c;
    let tour = [v + 1];
    let detailsTour = [v + 1];
    visited[v] = true;
    let cost = 0;

    for (let i = 0; i < n - 1; i++) {
        let w = -1;
        let minCost = Infinity;
        let stay = 0;
        for (let j = 0; j < n; j++) {
            if (c[v][j] < minCost && !visited[j]) {
                minCost = c[v][j];
                w = j;
                stay = j;
            }
        }
        detailsTour.push(minCost);
        tour.push(w + 1);
        // console.log(`${i} to thanh pho la ${stay}: ${minCost}\n`)
        cost += minCost;
        visited[w] = true;
        v = w;
    }

    cost += c[v][u_c];
    tour.push(u);
    return new TourResult(tour, detailsTour, cost);
}

// Cost matrix
const c = [
    [0, 20, 42, 31, 6, 24],
    [10, 0, 17, 6, 35, 18],
    [25, 5, 0, 27, 14, 9],
    [12, 9, 24, 0, 30, 12],
    [14, 7, 21, 15, 0, 38],
    [40, 15, 16, 5, 20, 0]
];


const readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});
const detail = [];
const resultTourMan = [];
// console.log("Nhập thành phố xuất phát u: ");

rl.question('Nhập thành phố xuất phát u: ', (answer) => {
  const u = parseInt(answer);

  const result = findTour(6, u, c);
  resultTourMan.push(...result.tour);
  detail.push(...result.detailTour);
  console.log("Chi Phi Toi Uu La:");
  let sizeList = resultTourMan.length;
  let currentIndex = 0;

  // Duyệt đường đi ngắn nhất của ma trận
  for (let x of resultTourMan) {
    if (currentIndex !== sizeList - 1) {
        process.stdout.write(x + " -> ");
    } else {
        console.log(x);
    }
    currentIndex++;
  }

  for (x of detail) {
    console.log(`${x} and `);
  }


  console.log("Cost: " + result.cost);
  
  rl.close();
});
