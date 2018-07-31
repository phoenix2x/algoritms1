let bubleSort = (arr) => {
  let compares = 0;
  let swaps = 0;
  for (let i = 0; i < arr.length - 1; i++) {
    let swapsCount = 0;
    for (let j = 0; j < arr.length - i - 1; j++) {
      compares++;
      if (arr[j] > arr[j + 1]) {
        swaps++;
        swapsCount++;
        swap(arr, j, j + 1);
      }
    }
    if(swapsCount === 0) {
      break;
    }
  }
  console.log('bubble-- compares: ' + compares + ' swaps: ' + swaps);
};

let insertionSort = (arr) => {
  let compares = 0;
  let swaps = 0;
  for (let i = 1; i < arr.length; i++) {
    let j = i;
    compares++;
    while (arr[j] < arr[j - 1] && j > 0) {
      compares++;
      swaps++;
      swap(arr, j, j - 1);
      j--;
    }
  }
  console.log('insertion-- compares: ' + compares + ' swaps: ' + swaps);
};

let selectionSort = (arr) => {
  let compares = 0;
  let swaps = 0;
  for(let i = 0; i < arr.length - 1;i++) {
    let minIndex = i;
    for(let j = i + 1; j < arr.length;j++) {
      compares++;
      if(arr[j] < arr[minIndex]) {
        swaps++;
        minIndex = j;
      }
    }
    swap(arr, i, minIndex);
  }
  console.log('selection-- compares: ' + compares + ' swaps: ' + swaps);
};

let shellSort = (arr) => {
  let h = 1;
  while(h < arr.length) {
    h = h * 3 + 1;
  }
  h = ~~(h/3);
  while(h > 0) {
    console.log(h);
    for(let i = h; i < arr.length; i++) {
      let j = i;
      while (j >= 0 && arr[j] < arr[j - h]) {
        swap(arr, j, j - h);
        j -= h
      }
    }
    h = ~~(h/3)
  }
};

const swap = (arr, i, j) => {
  const swp = arr[i];
  arr[i] = arr[j];
  arr[j] = swp;
};

// let arr = [1,2,3,4,5,10,6,7,8,9];
let arr = [10,9,8,7,6,5,4,3,2,1,0,-1,-2,-3,-4];
let arr1 = arr.slice();
let arr2 = arr.slice();
let arr3 = arr.slice();

// bubleSort(arr);
// console.log(arr);
// insertionSort(arr1);
// console.log(arr1);
// selectionSort(arr2);
// console.log(arr2);
shellSort(arr3);
console.log(arr3);
