import { Details } from "./details";

export interface SigninResponse {
    jwtToken: string,
    username: string,
    details: Details
}