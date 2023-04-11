export interface AuthResponse {
  ok: boolean;
  uid?: string;
  name?: string;
  email?: string;
  token?: string;
  msg?: string;
}

export interface Usuario {
  uid: string;
  name: string;
  email: string;
}

export interface Credentials {
  name: string;
  email: string;
  password: string;
  sex: string;
}
