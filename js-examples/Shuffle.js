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
