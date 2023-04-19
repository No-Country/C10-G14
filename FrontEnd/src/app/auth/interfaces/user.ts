import { Role } from './role';
import { Sex } from './sex';

export interface User {
  id: number;
  name: string;
  email: string;
  password: string;
  clientSince?: Date;
  Roles?: Role;
  profileImg?: null | string;
  weight?: number | null;
  height?: number | null;
  sex?: Sex;
  age?: number;
  token?: string;
}
