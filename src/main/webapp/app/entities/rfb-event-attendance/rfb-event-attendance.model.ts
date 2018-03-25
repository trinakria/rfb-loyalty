import {BaseEntity} from './../../shared';
import {RfbUser} from '../rfb-user';
import {RfbEvent} from '../rfb-event';

export class RfbEventAttendance implements BaseEntity {
    constructor(
        public id?: number,
        public attendanceDate?: any,
        public rfbEventDTO?: RfbEvent,
        public rfbUserDTO?: RfbUser,
    ) {
    }
}
