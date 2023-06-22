import { Airport } from "./airport"
import { BookedFlight } from "./booked-flight"

export interface Booking {
    flight: BookedFlight,
    source: Airport,
    destination: Airport
}