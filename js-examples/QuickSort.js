var random = (min, max) =>
  Math.floor(Math.random() * (max - min + 1)) + min;

var swap = (arr, i, j) => {
  var tmp = arr[i];
  arr[i] = arr[j];
  arr[j] = tmp;
};

var shuffle = arr => {
  for(var i = 0; i < arr.length; i++) {
    var r = random(0, i);
    swap(arr, r, i)
  }
};

var quickSort = arr => {
  shuffle(arr);
  // console.log(arr)
  quickSortRoutine(arr, 0, arr.length - 1);
};

var quickSortRoutine = (arr, start, finish) => {
  if(start >= finish) { return; }
  var pivot = partition(arr, start, finish);
  quickSortRoutine(arr, start, pivot - 1);
  quickSortRoutine(arr, pivot + 1, finish);
};


var partition = (arr, start, finish) => {
  var pivot = start;
  var i = start;
  var j = finish + 1;
  // console.log('s f',start, finish);
  while(true) {
    do{
      i++
    } while(arr[pivot] > arr[i] && i <= j);
    do {
      j--
    } while(arr[pivot] <= arr[j] && j >= i);
    // console.log(arr, i, j, pivot);
    if(i >= j) {
      break;
    }
    swap(arr, i, j);
  }
  // console.log(arr, i, j , pivot, start, finish);
  swap(arr, pivot, j);
  return j;
};

// var partition = (arr, start, finish) => {
//   var i = start;
//   var j = finish + 1;
//   while (true) {
//     while(arr[++i] < arr[start]) {
//       if(i === finish) break;
//     }
//     while(arr[start] < arr[--j]) {
//       if(j === start) break;
//     }
//     if(i >= j) break;
//     swap(arr, i, j)
//   }
//   console.log(arr, i, j , start, finish);
//   swap(arr, start, j);
//   return j;
// };

// var a = [9,8,7,6,5,4,3];
var a = [3, 9, 6, 7, 5, 8, 4];
// var a = [1, 2];

quickSort(a);

console.log(a);



