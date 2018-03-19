/* tslint:disable max-line-length */
import {async, ComponentFixture, fakeAsync, inject, TestBed, tick} from '@angular/core/testing';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Observable} from 'rxjs/Observable';
import {JhiEventManager} from 'ng-jhipster';

import {RfbloyaltyTestModule} from '../../../test.module';
import {RfbLocationDialogComponent} from '../../../../../../main/webapp/app/entities/rfb-location/rfb-location-dialog.component';
import {RfbLocationService} from '../../../../../../main/webapp/app/entities/rfb-location/rfb-location.service';
import {RfbLocation} from '../../../../../../main/webapp/app/entities/rfb-location/rfb-location.model';

describe('Component Tests', () => {

    describe('RfbLocation Management Dialog Component', () => {
        let comp: RfbLocationDialogComponent;
        let fixture: ComponentFixture<RfbLocationDialogComponent>;
        let service: RfbLocationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RfbloyaltyTestModule],
                declarations: [RfbLocationDialogComponent],
                providers: [
                    RfbLocationService
                ]
            })
            .overrideTemplate(RfbLocationDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RfbLocationDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RfbLocationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new RfbLocation(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.rfbLocation = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'rfbLocationListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new RfbLocation();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.rfbLocation = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'rfbLocationListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
