export interface Booked {
    flightNumber?: string,
    airline?: string,
    departureTime?: string,
    arrivalTime?: string,
    date?: string,
    source?: {iataCode: string, airportCity: string, airportName: string},
    destination?: {iataCode: string, airportCity: string, airportName: string},
    passengerCount?: number,
    passengerDetails?: {name: string, age: number, gender: string}[],
    seats?: string[],
    flightClass?: string,
    amountPaid?: number,
    paidBy?: string
}