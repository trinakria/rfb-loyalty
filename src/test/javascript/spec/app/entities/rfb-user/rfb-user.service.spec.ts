/* tslint:disable max-line-length */
import {async, TestBed} from '@angular/core/testing';
import {MockBackend} from '@angular/http/testing';
import {BaseRequestOptions, ConnectionBackend, Http, RequestOptions, Response, ResponseOptions} from '@angular/http';
import {JhiDateUtils} from 'ng-jhipster';

import {RfbUserService} from '../../../../../../main/webapp/app/entities/rfb-user/rfb-user.service';
import {RfbUser} from '../../../../../../main/webapp/app/entities/rfb-user/rfb-user.model';
import {SERVER_API_URL} from '../../../../../../main/webapp/app/app.constants';

describe('Service Tests', () => {

    describe('RfbUser Service', () => {
        let service: RfbUserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                providers: [
                    {
                        provide: ConnectionBackend,
                        useClass: MockBackend
                    },
                    {
                        provide: RequestOptions,
                        useClass: BaseRequestOptions
                    },
                    Http,
                    JhiDateUtils,
                    RfbUserService
                ]
            });

            service = TestBed.get(RfbUserService);

            this.backend = TestBed.get(ConnectionBackend) as MockBackend;
            this.backend.connections.subscribe((connection: any) => {
                this.lastConnection = connection;
            });
        }));

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(123).subscribe(() => {});

                expect(this.lastConnection).toBeDefined();

                const resourceUrl = SERVER_API_URL + 'api/rfb-users';
                expect(this.lastConnection.request.url).toEqual(resourceUrl + '/' + 123);
            });
            it('should return RfbUser', () => {

                let entity: RfbUser;
                service.find(123).subscribe((_entity: RfbUser) => {
                    entity = _entity;
                });

                this.lastConnection.mockRespond(new Response(new ResponseOptions({
                    body: JSON.stringify({id: 123}),
                })));

                expect(entity).toBeDefined();
                expect(entity.id).toEqual(123);
            });

            it('should propagate not found response', () => {

                let error: any;
                service.find(123).subscribe(null, (_error: any) => {
                    error = _error;
                });

                this.lastConnection.mockError(new Response(new ResponseOptions({
                    status: 404,
                })));

                expect(error).toBeDefined();
                expect(error.status).toEqual(404);
            });
        });
    });

});