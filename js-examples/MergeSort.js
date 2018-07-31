var mergeSort = (arr) => {
  mergeSortRoutine(arr, arr.slice(), 0, arr.length);
};

var mergeSortRoutine = (arr, temp, start, finish) => {
  var middle = getMiddle(start, finish);
  if(finish - start < 2) {
    // cutoff to insertion sort on small sub-arrays
    //if(finish - start < 7) {insertionSort(arr); return;}
    return;
  }
  mergeSortRoutine(arr, temp, start, middle);
  mergeSortRoutine(arr, temp, middle, finish);
  if(arr[middle - 1] < arr[middle]) {
    return;
  }
  merge(arr, temp, start, finish, middle);
};

var getMiddle = (start, finish) => {
  return start + Math.ceil((finish - 1 - start) / 2);
};
var merge = (arr, temp, start, finish, middle) => {
  console.log('before' + arr.slice(start, finish));
  for(var k = start; k < finish; k++) {
    temp[k] = arr[k];
  }
  var i = start;
  var j = middle;
  for(var k = start; k < finish; k++) {
    if(i === middle) {
      arr[k] = temp[j++]
    } else if(j === finish) {
      arr[k] = temp[i++]
    } else if(temp[i] <= temp[j]) {
      arr[k] = temp[i++]
    } else {
      arr[k] = temp[j++]
    }
  }
  console.log('after' + arr.slice(start, finish));
};

var arr = [10,9,8,7,6,5,4,3,2];
// var arr = [1,2,3,4,5,6,7,8,9];
var arr1 = arr.slice();

mergeSort(arr1);
console.log(arr1);
