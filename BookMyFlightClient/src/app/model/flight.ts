export interface Flight {
    flightNumber: string,
    departureTime: string,
    arrivalTime: string,
    airline: string,
    date: Date,
    source: string,
    destination: string,
    status: string,
    prices: number[]
}