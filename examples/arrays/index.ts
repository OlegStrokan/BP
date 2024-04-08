const array = [1, 2, 3, 4, 5]
const sumOfElements = array.reduce(
    (accumulator, currentValue) => accumulator + currentValue,
    0,
)
console.log('Sum of elements in the array:', sumOfElements)
