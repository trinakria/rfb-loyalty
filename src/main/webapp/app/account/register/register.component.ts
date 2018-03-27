import {AfterViewInit, Component, ElementRef, OnInit, Renderer} from '@angular/core';
import {NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

import {Register} from './register.service';
import {EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE, LoginModalService, ResponseWrapper} from '../../shared';
import {RfbLocation, RfbLocationService} from '../../entities/rfb-location';

@Component({
    selector: 'jhi-register',
    templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit, AfterViewInit {

    confirmPassword: string;
    doNotMatch: string;
    error: string;
    errorEmailExists: string;
    errorUserExists: string;
    registerAccount: any;
    success: boolean;
    modalRef: NgbModalRef;
    locations: RfbLocation[];

    constructor(
        private loginModalService: LoginModalService,
        private registerService: Register,
        private elementRef: ElementRef,
        private renderer: Renderer,
        private locationService: RfbLocationService
    ) {
    }

    ngOnInit() {
        this.success = false;
        this.registerAccount = {
            homeLocation: null
        };
        this.loadLocations();
    }

    ngAfterViewInit() {
        this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#login'), 'focus', []);
    }

    register() {
        if (this.registerAccount.password !== this.confirmPassword) {
            this.doNotMatch = 'ERROR';
        } else {
            this.doNotMatch = null;
            this.error = null;
            this.errorUserExists = null;
            this.errorEmailExists = null;
            this.registerAccount.langKey = 'en';
            this.registerService.save(this.registerAccount).subscribe(() => {
                this.success = true;
            }, (response) => this.processError(response));
        }
    }

    openLogin() {
        this.modalRef = this.loginModalService.open();
    }

    loadLocations() {
        this.locations = [];
        this.locationService.query( {
            page: 0,
            size: 100,
            sort: ['locationName,runDayOfWeek', 'ASC']}).subscribe(
            (res: ResponseWrapper) => {
                this.locations = res.json
            },
            (res: ResponseWrapper) => {
                console.log(res)
            }
        );
    }

    private processError(response) {
        this.success = null;
        if (response.status === 400 && response.json().type === LOGIN_ALREADY_USED_TYPE) {
            this.errorUserExists = 'ERROR';
        } else if (response.status === 400 && response.json().type === EMAIL_ALREADY_USED_TYPE) {
            this.errorEmailExists = 'ERROR';
        } else {
            this.error = 'ERROR';
        }
    }
}
