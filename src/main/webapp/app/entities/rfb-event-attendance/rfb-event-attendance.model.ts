import {BaseEntity, User} from './../../shared';
import {RfbEvent} from '../rfb-event';

export class RfbEventAttendance implements BaseEntity {
    constructor(
        public id?: number,
        public attendanceDate?: any,
        public rfbEventDTO?: RfbEvent,
        public userDTO?: User,
    ) {
    }
}
